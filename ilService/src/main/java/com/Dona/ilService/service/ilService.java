package com.Dona.ilService.service;

import com.Dona.ilService.exception.ilAlreadyExistsException;
import com.Dona.ilService.exception.ilNotFoundException;
import com.Dona.ilService.model.il;
import com.Dona.ilService.repository.ilRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class ilService {

    private final ilRepository ilRepository;

    public List<il> getIller(String name) {
        if (name == null) {
            return ilRepository.findAll();
        } else {
            return ilRepository.findAllByName(name);
        }
    }

    public il createIl(il newIl) {
        Optional<il> ilByName = ilRepository.findByName(newIl.getName());
        if (ilByName.isPresent()) {
            throw new ilAlreadyExistsException("Il already exists with name: " + newIl.getName());
        }
        return ilRepository.save(newIl);

    }

    public void deleteIl(String id) {
        ilRepository.deleteById(id);
    }

    public il getIlById(String id) {
        return ilRepository.findById(id)
                .orElseThrow(() -> new ilNotFoundException("Il not found with id: " + id));
    }

    public void updateIl(String id, il newIl) {
        il oldIl = getIlById(id);
        //sadece ismini değiştirdik
        oldIl.setName(newIl.getName());
        ilRepository.save(oldIl);
    }
}
