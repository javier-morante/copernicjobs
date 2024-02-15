package cat.copernic.backend.services.user_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.user_management.CompanyDTO;
import cat.copernic.backend.data.dto.user_management.CreateCompanyDTO;
import cat.copernic.backend.data.dto.user_management.UpdateCompanyDTO;
import cat.copernic.backend.data.models.company.Company;
import cat.copernic.backend.data.models.company.CompanyService;

import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateCompanyData;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDNIException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class CompanyManagementService {
    
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ValidateCompanyData validateCompanyData;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Response createCompany(CreateCompanyDTO body) {
        try {
            this.validateCompanyData.validateCompanyCreationData(body);

            this.companyService.save(new Company(
                body.getName(), 
                body.getEmail(), 
                body.getNif(), 
                body.getPhone(), 
                this.passwordEncoder.encode(body.getPassword()),
                null, 
                true, 
                "No Description", 
                null,
                "Not Web Page URL", 
                true)
            );

            return new Response(
                ResponseState.OK,
                "Company user Created!"
            );

        } catch (InvalidUserNameException | InvalidPhoneException | InvalidEmailException | 
            InvalidDNIException ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        }catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                "Not Ok"
            );
        }
    }

    public Response getCompanies() {
        try {
            List<Company> companies = this.companyService.getAll();
            List<CompanyDTO> processedCompanies = new ArrayList<CompanyDTO>();

            for (Company company : companies) {
                processedCompanies.add(new CompanyDTO(
                    company.getUserId(),
                    company.getName(),
                    company.getNif(),
                    company.getPhone(),
                    company.getEmail(),
                    company.getIsEnabled()
                ));
            }

            return new Response(
                ResponseState.OK,
                new Gson().toJson(processedCompanies)
            );

        } catch (Exception e) {
            return new Response(
                ResponseState.ERROR,
                "Can't fetch the users"
            );
        }
    }

    public Response updateCompany(UpdateCompanyDTO body) {
        try {
            Company company = this.companyService.getById(body.getUserId());

            company.setName(body.getName());
            company.setNif(body.getNif());
            company.setPhone(body.getPhone());
            company.setEmail(body.getEmail());

            //this.validateCompanyData.validateCompany(company);

            this.companyService.update(company);

            return new Response(
                ResponseState.OK,
                "User Updated Successfully!"
            );

        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        }
    }

}
