package main;

import main.entity.Diagnosis;
import main.entity.Patient;
import main.entity.User;
import main.entity.Ward;
import main.repository.DiagnosisRepository;
import main.repository.PatientRepository;
import main.repository.UserRepository;
import main.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class TestDataInit implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder pwdEncoder;

    @Override
    public void run(String[] args) throws Exception {
        Ward w = new Ward("first", 10l);
        Diagnosis d = new Diagnosis("second");
        wardRepository.save(w);
        wardRepository.save(new Ward("second", 20l));
        diagnosisRepository.save(new Diagnosis("first"));
        diagnosisRepository.save(d);
        patientRepository.save(new Patient("Q", "W", "E", d, w ));

        userRepository.save(new User("user", pwdEncoder.encode("pwd"), Collections.singletonList("ROLE_USER")));
        userRepository.save(new User("admin", pwdEncoder.encode("apwd"), Collections.singletonList("ROLE_ADMIN")));
    }
}
