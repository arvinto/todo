package todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import todo.model.User;
import todo.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by bill.villaflor on 6/29/16.
 */
@Repository
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl( UserRepository repository ){

        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = Optional.ofNullable( repository.findByUserName( username ) );

        return user.map( u -> new UserDetailsImpl( u.getUserName(), u.getPassword() ) )
                   .orElseThrow( () -> new UsernameNotFoundException( "Can't find user: " + username ) );
    }

    private class UserDetailsImpl implements UserDetails {

        private String userName;

        private String password;


        public UserDetailsImpl( String userName, String password ){

            this.userName = userName;
            this.password = password;

        }

        @Override
        public Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
            return Arrays.asList( new GrantedAuthorityImpl( "ROLE_USER" ) );
        }

        @Override
        public String getPassword() {

            return password;
        }

        @Override
        public String getUsername() {

            return userName;
        }

        @Override
        public boolean isAccountNonExpired() {

            return true;
        }

        @Override
        public boolean isAccountNonLocked() {

            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {

            return true;
        }

        @Override
        public boolean isEnabled() {

            return true;
        }
    }

    private class GrantedAuthorityImpl implements GrantedAuthority{

        private String authority;

        public GrantedAuthorityImpl( String authority ){

            this.authority = authority;
        }

        @Override
        public 	String getAuthority(){

            return authority;
        }

    }
}
