package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.ElectionInput;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.output.v1.VoteOutput;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import br.edu.ulbra.election.election.repository.VoteRepository;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ElectionService {

    private final VoteRepository electionRepository;

    private final ModelMapper modelMapper;

    private static final String MESSAGE_INVALID_ID = "Invalid id";
    private static final String MESSAGE_ELECTION_NOT_FOUND = "Election not found";

    @Autowired
    public ElectionService(VoteRepository electionRepository, ModelMapper modelMapper){

        this.electionRepository = electionRepository;
        this.modelMapper = modelMapper;

    }

    public List<VoteOutput> getAll(){
        Type electionOutputListType = new TypeToken<List<VoteOutput>>(){}.getType();
        return modelMapper.map(electionRepository.findAll(), electionOutputListType);
    }

    public VoteOutput create(ElectionInput electionInput) {
        validateInput(electionInput, false);
        Election election = modelMapper.map(electionInput, Election.class);
        election = electionRepository.save(election);
        return modelMapper.map(election, VoteOutput.class);
    }

    public VoteOutput getByYear(Integer year){
        if (year == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        Election election = electionRepository.findByYear(year).orElse(null);

        if (election == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        return modelMapper.map(election, VoteOutput.class);
    }

    public VoteOutput getById(Long id){
        if (id == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Election election = electionRepository.findById(id).orElse(null);
        if (election == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        return modelMapper.map(election, VoteOutput.class);
    }

    public VoteOutput update(Long id, ElectionInput electionInput) {
        if (id == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }
        validateInput(electionInput, true);

        Election election = electionRepository.findById(id).orElse(null);
        if (election == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        try {
            election.setDescription(electionInput.getDescription());
            //election.setId(electionInput.getId());
            election.setStateCode(electionInput.getStateCode());
            election.setYear(electionInput.getYear());
        }catch (Exception e){
            System.out.println("Erro ao setar valores");
        }

        election = electionRepository.save(election);
        return modelMapper.map(election, VoteOutput.class);
    }

    public GenericOutput delete(Long id) {
        if (id == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Election election = electionRepository.findById(id).orElse(null);
        if (election == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        electionRepository.delete(election);

        return new GenericOutput("Election deleted");
    }

    private void validateInput(ElectionInput electionInput, boolean isUpdate){
        if (StringUtils.isBlank(electionInput.getDescription())){
            throw new GenericOutputException("Invalid description");
        }
        if (electionInput.getDescription().length() < 5){
            throw new GenericOutputException("Invalid description. Min. 5 letters");
        }
        if (StringUtils.isBlank(electionInput.getStateCode())){
            throw new GenericOutputException("Invalid state code");
        }
        if (electionInput.getYear() < 2000 || electionInput.getYear() >= 2200){
            throw new GenericOutputException("Invalid year");
        }
        String year = electionInput.getYear().toString();
        if (StringUtils.isBlank(year)){
            throw new GenericOutputException("Invalid year");
        }
        String [] state = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RR", "RO", "RJ", "RN", "RS", "SC", "SP", "SE", "TO", "BR"};
        int i = 0;
        for(String stateCode:state){
            if (stateCode.equalsIgnoreCase(electionInput.getStateCode())){
                i = 1;
                break;
            }
        }
        if (i == 0){
            throw new GenericOutputException("Invalid state code");
        }

       // } else {
            //if (!isUpdate) {
              //  throw new GenericOutputException("Password doesn't match");
            //}
        //}
    }

}
