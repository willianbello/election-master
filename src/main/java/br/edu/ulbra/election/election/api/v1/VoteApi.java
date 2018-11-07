package br.edu.ulbra.election.election.api.v1;

import br.edu.ulbra.election.election.input.v1.ElectionInput;
import br.edu.ulbra.election.election.input.v1.VoteInput;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import br.edu.ulbra.election.election.service.ElectionService;
import br.edu.ulbra.election.election.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/vote")
public class VoteApi {

    private final VoteService voteService;

    @Autowired
    public VoteApi(VoteService voteService){ this.voteService = voteService;}

    @PutMapping("/{electionId}")
    public GenericOutput electionVote(@RequestBody VoteInput voteInput){
        return voteService.electionVote(voteInput);
        //return new GenericOutput("OK");
    }

    @PutMapping("/multiple")
    public GenericOutput multipleElectionVote(@RequestBody List<ElectionInput> voteInputList){
        return voteService.multipleElectionVote(voteInputList);
        //return new GenericOutput("OK");
    }
}
