package ru.twemi.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.twemi.dto.security.UserDto;
import ru.twemi.entity.Authority;
import ru.twemi.entity.User;
import ru.twemi.exception.UserAlreadyExistException;
import ru.twemi.repository.AuthorityRepo;
import ru.twemi.repository.UserRepo;
import ru.twemi.services.UserService;
import ru.twemi.services.mapper.UserMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class JpaUserDetailService implements UserDetailsService, UserService {

    private final UserRepo userRepo;
    private final AuthorityRepo authorityRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username: " + username + " not found")
        );

    }

    @Override
    public UserDto register(UserDto userDto) {
        if (userRepo.findByUsername(userDto.getUsername()).isPresent()) {
            throw  new UserAlreadyExistException();
        }
        User user = userMapper.toUser(userDto);
        Authority authority = authorityRepo.findByRole("USER");

        user.setAuthorities(Set.of(authority));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User registeredAccountUser = userRepo.save(user);
        log.debug("User with username {} was registered successfully", registeredAccountUser.getUsername());
        return userMapper.toUserDto(registeredAccountUser);
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        User user = userMapper.toUser(userDto);
//        if (user.getId() != null) {
//            userRepo.findById(userDto.getId()).ifPresent(
//                    (p) -> {
//                        user.setVersion(p.getVersion());
//                        user.setStatus(p.getStatus());
//                    }
//            );
//        }
        return userMapper.toUserDto(userRepo.save(user));
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username: " + username + " not found")
        );
    }

    //    @Transactional
    public User update(User accountUser) {
        if (accountUser.getId() != null) {
            userRepo.findById(accountUser.getId());
        }
        return userRepo.save(accountUser);
    }


    @Override
    @Transactional()
    public UserDto findById(Long id) {
        return userMapper.toUserDto(userRepo.findById(id).orElse(null));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        final User user = userRepo.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User with id %s not found", id)
                )
        );
        update(user);
    }

//    private void enable(final AccountUser accountUser) {
//        accountUser.setStatus(AccountStatus.ACTIVE);
//        accountUser.setAccountNonLocked(true);
//        accountUser.setAccountNonExpired(true);
//        accountUser.setEnabled(true);
//        accountUser.setCredentialsNonExpired(true);
//    }

//    private void disable(final AccountUser accountUser) {
//        accountUser.setStatus(AccountStatus.DELETED);
//        accountUser.setAccountNonLocked(false);
//        accountUser.setAccountNonExpired(false);
//        accountUser.setEnabled(false);
//        accountUser.setCredentialsNonExpired(false);
//    }
}
