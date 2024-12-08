package com.example.backend.restaurante.service.user;

import com.example.backend.restaurante.model.user.Endereco;
import com.example.backend.restaurante.repository.user.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {


        @Autowired
        private EnderecoRepository enderecoRepository;

        public Endereco saveEndereco(Endereco endereco) {
            return enderecoRepository.save(endereco);
        }

        public List<Endereco> getAllEnderecos() {
            return enderecoRepository.findAll();
        }

        public Endereco getEnderecoById(Long id) {
            Optional<Endereco> endereco = enderecoRepository.findById(id);
            return endereco.orElse(null);
        }

        public Endereco updateEndereco(Long id, Endereco updateEndereco) {
            Optional<Endereco> endereco = enderecoRepository.findById(id);
            if (endereco.isPresent()) {
                Endereco existingEndereco = endereco.get();
                existingEndereco.setRua(updateEndereco.getRua());
                existingEndereco.setNumero(updateEndereco.getNumero());
                existingEndereco.setBairro(updateEndereco.getBairro());
                existingEndereco.setCidade(updateEndereco.getCidade());
                existingEndereco.setObservacao(updateEndereco.getObservacao());

                return enderecoRepository.save(existingEndereco);
            } else {
                return null;
            }
        }

        public void deleteEndereco(Long id) {
            enderecoRepository.deleteById(id);
        }
}


