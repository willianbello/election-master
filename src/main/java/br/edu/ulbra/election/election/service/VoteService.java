package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.input.v1.ElectionInput;
import br.edu.ulbra.election.election.input.v1.VoteInput;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import br.edu.ulbra.election.election.output.v1.VoteOutput;
import br.edu.ulbra.election.election.repository.VoteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.geom.GeneralPath;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public VoteService(VoteRepository voteRepository, ModelMapper modelMapper){

        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
    }

    public GenericOutput electionVote(VoteInput voteInput){

        return new GenericOutput("OK");
    }

    public GenericOutput multipleElectionVote(List<ElectionInput> voteInputList) {

        return new GenericOutput("OK");
    }
}

