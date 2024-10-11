package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;//hover over it press alt +enter then add constructor parameter
    // this part of the code will not work go to -> maven repository then select modelMapper the select dependency
    // then paste that dependency into the pom.xml file ALSO CLICK TOP CORNOR ICON
    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }//constructor dependency injection

    public List<Registration> getRegistrations(){//return type is list
        List<Registration> registrations = registrationRepository.findAll();//stored into the Entity list
        return registrations;// returning that
    }

    public Registration createRegistration(Registration registration) {//sava the data into the db
       Registration savedEntity = registrationRepository.save(registration);
       return savedEntity;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);

    }

    public Registration updateRegistration(long id, Registration registration) {//now Update the record 
        Registration r = registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
                return r;
    }
    public RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto1 = modelMapper.map(registration, RegistrationDto.class);
       /* RegistrationDto dto = new RegistrationDto();
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        */
        return dto1;
    }
    public Registration mapToEntity(RegistrationDto dto){
        Registration reg = modelMapper.map(dto,Registration.class);
        //Registration registration = new Registration();
        //registration.setName(dto.getName());
        //registration.setEmail(dto.getEmail());
        //registration.setMobile(dto.getMobile());
        // Registration reg1 = registrationRepository.save(registration);
                return reg;
    }

    public RegistrationDto getRegistration(long id) {
        //Registration reg = registrationRepository.findById(id).get();
        Registration reg =  registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Record Not Found MataJi")
        );
      return  mapToDto(reg);
    }
}
