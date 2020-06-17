package com.greeting.service.impl;

import com.greeting.dao.GreetingDao;
import com.greeting.model.Users;
import com.greeting.model.GreetingDto;
import com.greeting.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
		
	@Autowired
	private GreetingDao userDao;

	public List<Users> findAll() {
		List<Users> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		try {
			userDao.deleteById(Long.valueOf(id));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Users findOne(String username) {
		return userDao.findByfirstName(username);
	}

	@Override
	public Users findById(int id) {
		Optional<Users> optionalUser = userDao.findById(Long.valueOf(id));
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public GreetingDto update(GreetingDto userDto) {
        Users user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password", "username");
            userDao.save(user);
        }
        return userDto;
    }

    
    
    @Override
    public Users save(GreetingDto user) {
	    Users newUser = new Users();
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
        return userDao.save(newUser);
    }
}
