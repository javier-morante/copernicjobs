package cat.copernic.backend.services.companyProfileModal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.companyProfileModal.CompanyProfileModalDTO;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.data.models.company.CompanyService;

@Service
public class CompanyProfileModalService {
    @Autowired
    private CompanyService companyDao;

    private ModelMapper modelMapper = new ModelMapper();

    public Response getCompanyData(String nif) {
        CompanyProfileModalDTO company = modelMapper.map(companyDao.getByNif(nif), CompanyProfileModalDTO.class);
        return new Response(200, new Gson().toJson(company));
    }
}
