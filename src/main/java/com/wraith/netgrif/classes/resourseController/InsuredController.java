package com.wraith.netgrif.classes.resourseController;

import com.google.gson.Gson;
import com.wraith.netgrif.classes.FullAccount;
import com.wraith.netgrif.classes.db.Account;
import com.wraith.netgrif.classes.db.PropertyInsurance;
import com.wraith.netgrif.classes.db.TravelInsurance;
import com.wraith.netgrif.classes.exceptions.AccountNotFoundException;
import com.wraith.netgrif.classes.exceptions.CreateAccountException;
import com.wraith.netgrif.classes.exceptions.PropertyInsuranceException;
import com.wraith.netgrif.classes.exceptions.TravelInsuranceException;
import com.wraith.netgrif.classes.services.AccountQueryService;
import com.wraith.netgrif.classes.services.PropertyInsuranceQueryService;
import com.wraith.netgrif.classes.services.TravelInsuranceQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class InsuredController
{
    private final AccountQueryService accountQueryService;
    private final TravelInsuranceQueryService travelInsuranceQueryService;
    private final PropertyInsuranceQueryService propertyInsuranceQueryService;

    public InsuredController(AccountQueryService accountQueryService, TravelInsuranceQueryService travelInsuranceQueryService, PropertyInsuranceQueryService propertyInsuranceQueryService)
    {
        this.accountQueryService = accountQueryService;
        this.travelInsuranceQueryService = travelInsuranceQueryService;
        this.propertyInsuranceQueryService = propertyInsuranceQueryService;
    }

    @GetMapping("/insured")
    public String insuredController()
    {
        Gson gson = new Gson();
        return gson.toJson(accountQueryService.getAccounts());
    }

    @RequestMapping(value = "/insured", method = RequestMethod.POST, headers = "Accept=application/json")
    public String insuredCreateController(@RequestBody String data)
    {
        try
        {
            long ID;

            //I can create account in db by using parsedObject.getAccount, but I'm creating new object to
            //control data in object, 'cause Gson.fromJson doesn't throw class exceptions

            StringBuilder response = new StringBuilder();
            TravelInsurance travelInsurance;
            PropertyInsurance propertyInsurance;

            FullAccount parsedObject = new Gson().fromJson(data, FullAccount.class);
            List<TravelInsurance> travelInsuranceList = parsedObject.getTravelInsurance();
            List<PropertyInsurance> propertyInsuranceList = parsedObject.getPropertyInsurance();

            Account preview = parsedObject.getAccount();

            if(preview == null)
                throw new CreateAccountException("Invalid account object");

            Account account = new Account();

            if(accountQueryService.getAccountByMail(preview.getMail()) != null)
                throw new CreateAccountException("Account with this mail already exist");

            account.setFirstName(preview.getFirstName());
            account.setLastName(preview.getLastName());
            account.setIdentNumber(preview.getIdentNumber());
            account.setMail(preview.getMail());
            account.setAddress(preview.getAddress());
            account.setCorrAddress(preview.getCorrAddress());

            ID = accountQueryService.createAccount(account);
            response.append(ID).append("\n\n");

            if(ID > 0) {
                if (travelInsuranceList != null) {
                    for (TravelInsurance insurance : travelInsuranceList) {

                        try {

                            travelInsurance = new TravelInsurance();

                            travelInsurance.setOwnerID(ID);
                            travelInsurance.setDateStart(insurance.getDateStart());
                            travelInsurance.setDateEnd(insurance.getDateEnd());
                            travelInsurance.setDamageLiability(insurance.isDamageLiability());
                            travelInsurance.setUsed(insurance.isUsed());

                            response.append(travelInsuranceQueryService.createInsurance(travelInsurance)).append(" ").append(travelInsurance.getDateStart()).append("\n");
                        }
                        catch (TravelInsuranceException exception) { response.append(exception.getMessage()).append('\n'); }
                    }
                }

                if (propertyInsuranceList != null) {
                    for (PropertyInsurance insurance : propertyInsuranceList) {
                        try {

                            propertyInsurance = new PropertyInsurance();

                            propertyInsurance.setOwnerID(ID);
                            propertyInsurance.setDate(insurance.getDate());
                            propertyInsurance.setType(insurance.getIntType());
                            propertyInsurance.setAddress(insurance.getAddress());
                            propertyInsurance.setPrice(insurance.getPrice());

                            propertyInsuranceQueryService.createInsurance(propertyInsurance);
                        }
                        catch (PropertyInsuranceException exception) { response.append(exception.getMessage()).append('\n'); }
                    }
                }
            }

            return response.toString();
        }
        catch (CreateAccountException exception) { return "Error while creating user.\nError: " + exception.getMessage(); }
        catch (Exception exception) { return "Error occurred: " + exception.getMessage(); }
    }

    @GetMapping("/insured/{inputID}")
    public ResponseEntity<FullAccount> insuredIDController(@PathVariable("inputID") String inputID)
    {
        try
        {
            long ID = Long.parseLong(inputID);
            Optional<Account> account = accountQueryService.getAccountByID(ID);

            if(account.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            List<TravelInsurance> travelInsurance = travelInsuranceQueryService.getInsuranceByOwnerID(account.get().getID());
            List<PropertyInsurance> propertyInsurances = propertyInsuranceQueryService.getInsuranceByOwnerID(account.get().getID());

            return new ResponseEntity<>(new FullAccount(account.get(), travelInsurance, propertyInsurances), HttpStatus.OK);
        }
        catch (NumberFormatException exception) { throw new AccountNotFoundException("Invalid ID"); }
        catch (ResponseStatusException exception) { throw new AccountNotFoundException("Empty"); }
    }
}
