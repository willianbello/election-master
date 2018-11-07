package br.edu.ulbra.election.election.model;

import javax.persistence.*;

@Entity
@Table (name="Election")
public class Vote {

    @Id
    @Column(nullable = false)
    private Long electionId;

    @Column(nullable = false)
    private Long voterId;

    @Column(nullable = false)
    private Long candidateId;

    public Long getElectionId() { return electionId; }

    public void setElectionId(Long electionId) { this.electionId = electionId; }

    public Long getVoterId() { return voterId; }

    public void setVoterId(Long voterId) { this.voterId = voterId; }

    public Long getCandidateId() { return candidateId; }

    public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }
}
