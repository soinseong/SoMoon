package kr.somoon.somoonshop.webservice.service;

import kr.somoon.somoonshop.webservice.domain.account.Account;
import kr.somoon.somoonshop.webservice.domain.account.AccountRepository;
import kr.somoon.somoonshop.webservice.exepction.UnAuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountServcie {

    @Autowired
    private AccountRepository accountRepository;

//    @Transactional
//    public Long save(AccountSaveRequestDto dto){
//        return accountRepository.save(dto.toEntity()).getNo();
//    }
//
//    @Transactional
//    public List<AccountMainResponseDto> findAllDesc(){
//        return accountRepository.findAllDesc()
//                .map(AccountMainResponseDto::new)
//                .collect(Collectors.toList());
//    }

    public Account login(String accountId, String accountPassword) throws UnAuthenticationException {
        Optional<Account> user = accountRepository.findByAccountId(accountId);
        if(!user.isPresent())
            throw new IllegalStateException();

        Account account = user.get();
        if(!account.matchPassword(accountPassword))
            throw new IllegalStateException();
        return account;
    }
}
