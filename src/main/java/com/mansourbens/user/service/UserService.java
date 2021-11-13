package com.mansourbens.user.service;

import com.mansourbens.user.UserNotFoundException;
import com.mansourbens.user.VO.Department;
import com.mansourbens.user.VO.ResponseTemplateVO;
import com.mansourbens.user.entity.User;
import com.mansourbens.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) throws NoSuchElementException {
        ResponseTemplateVO vo = new ResponseTemplateVO();
           User user = userRepository.findById(userId).get();
           Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                   Department.class);

           vo.setUser(user);
           vo.setDepartment(department);
           return vo;
    }
}
