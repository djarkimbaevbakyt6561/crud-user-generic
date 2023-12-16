package service.impls;

import models.User;
import service.Service;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements Service<User>{
        List<User> users = new ArrayList<>();

        @Override
        public Boolean add(User user) {
            return users.add(user);    }


        @Override
        public List<User> get() {
            return users;
        }

        @Override
        public String update(User newUpdatedUser, String userEmail) {
            for (int i = 0; i < users.size(); i ++) {
                if(users.get(i).getEmail().equals(userEmail)){
                    users.set(i, newUpdatedUser);
                    return "Успешно изменено!";
                }
            }
            return "Неправильный email";
        }

        @Override
        public Boolean delete(User user) {
            return users.remove(user);
        }
}
