package org.ancloud.repository.mappers;

import java.util.List;

import org.ancloud.domain.account.Account;

public interface AccountMapper {
  public List<Account> findAll();
}
