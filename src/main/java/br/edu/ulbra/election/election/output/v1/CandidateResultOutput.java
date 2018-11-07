package br.edu.ulbra.election.election.output.v1;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Candidate Result Output Information")
public class CandidateResultOutput {

    @ApiModelProperty(notes = "Election Information")
    private VoteOutput election;

    @ApiModelProperty(notes = "Candidate Information")
    private CandidateOutput candidate;

    @ApiModelProperty(notes = "Total Votes")
    private Long totalVotes;

    public VoteOutput getElection() {
        return election;
    }

    public void setElection(VoteOutput election) {
        this.election = election;
    }

    public CandidateOutput getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateOutput candidate) {
        this.candidate = candidate;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }
}
