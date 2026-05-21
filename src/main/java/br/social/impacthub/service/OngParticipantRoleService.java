package br.social.impacthub.service;

import br.social.impacthub.infrastructure.persistence.OngParticipantRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OngParticipantRoleService {
    private final OngParticipantRoleRepository ongParticipantRoleRepository;

    public OngParticipantRoleService(OngParticipantRoleRepository ongParticipantRoleRepository) {
        this.ongParticipantRoleRepository = ongParticipantRoleRepository;
    }

    public List<String> getAll(){
        return ongParticipantRoleRepository.findAll().stream()
                .map(role -> role.getName())
                .toList();
    }
}
