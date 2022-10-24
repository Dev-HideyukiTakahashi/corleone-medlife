package br.com.corleone.medlife.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  private ImplementacaoUserDetailsService userDetails;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable() // desativa configuracoes padroes
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/admin/**").hasAnyRole("ADMIN")
        .antMatchers("/medico/**").hasAnyRole("ADMIN", "MEDICO")
        .antMatchers("/consultas/**").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
        .antMatchers("/pacientes/**").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
        .antMatchers("/contatos").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
        .antMatchers("/home").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
        .and()
        .exceptionHandling().accessDeniedPage("/negado")
        .and().formLogin().loginPage("/login").permitAll()
        .defaultSuccessUrl("/home")
        .failureUrl("/login?error=true")
        .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/").permitAll();
    ;
  }

  @Override // cria autenticacao com banco de dados ou em memoria
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetails)
        .passwordEncoder(new BCryptPasswordEncoder());

    /*
     * Em memoria para teste
     * auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance
     * ())
     * .withUser("admin")
     * .password("admin")
     * .roles("ADMIN");
     */
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/static/**");
    web.ignoring().antMatchers("/h2-console/**");
  }
}
