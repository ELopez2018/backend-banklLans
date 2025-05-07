package com.banklLans.application.service.interfaces;




import com.banklLans.domain.model.User;

import java.util.List;

public interface UserInterface {

    List<User> getAll();

    User getUserByID(Long userId);

    Boolean delete(User user);

    User update(User user);
}
