package edu.cqu.no1.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqu.no1.domain.TUser;
import edu.cqu.no1.service.UserService;
import edu.cqu.no1.util.MD5Util;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller

@Namespace("/")
@Scope("prototype")
@ParentPackage("base")
public class UserAction extends ActionSupport {
    private static final long serialVersionUID = -8331089822539279241L;

    @Resource
    private UserService userService;

    private TUser user;
    private List<TUser> listUsers;
    private String id;

    private String userId;
    private String userName;
    private String userType;
    private String userState;
    private String validateCode;
    private String password;

    private String newPassword;
    private Boolean statu;


    @Action(value = "changePassword", results = {
            @Result(name = "success", type = "json", location = "statu")
    })
    public String changePassword() {
        String md5Pwd = MD5Util.MD5(password);
        if (userService.userLogin(userId, md5Pwd) == null) {
            statu = false;
        } else {
            String newMd5Pwd = MD5Util.MD5(newPassword);
            this.userService.changePassword(userId, newMd5Pwd);
            statu = true;
        }
        return SUCCESS;
    }


    @Action(value = "userLogin", results = {
            @Result(name = "login", location = "/login.jsp"),
            @Result(name = "student", location = "/student_index.jsp"),
            @Result(name = "teacher", location = "/teacher_index.jsp"),
            @Result(name = "jiaowuchu", location = "/dean_index.jsp")
    })
    public String userLogin() throws Exception {

        return SUCCESS;
    }


    @Action(value = "AddUser", results = {
            @Result(name = "input", location = "/pages/systemManage/userManage/user_add.jsp"),
            @Result(name = "success", type = "redirect", location = "ListUser")
    })
    public String addUser() throws Exception {
        try {
            this.userService.addTUser(user);

            return "success";
        } catch (Exception e) {
            System.out.println("add exception: " + e.toString());

            return "addError";
        }
    }

    @Action(value = "PreAddUser", results = {
            @Result(name = "success", location = "/pages/systemManage/userManage/user_add.jsp")
    })
    public String preAddUser() throws Exception {
        return "success";
    }


    @Action(value = "ViewUser", results = {
            @Result(name = "success", location = "/pages/systemManage/userManage/user_view.jsp")
    })
    public String viewUser() throws Exception {
        try {
            user = this.userService.getTUser(id);

            return "success";
        } catch (Exception e) {
            return "viewError";
        }
    }


    @Action(value = "UpdateUser", results = {
            @Result(name = "input", location = "/pages/systemManage/userManage/user_edit.jsp"),
            @Result(name = "success", type = "redirect", location = "ListUser")
    })
    public String updateUser() throws Exception {
        try {
            this.userService.updateTUser(user);

            return "success";
        } catch (Exception e) {
            System.out.println("update exception: " + e.toString());

            return "updateError";
        }
    }


    @Action(value = "DeleteUser", results = {
            @Result(name = "success", type = "redirect", location = "ListUser")
    })
    public String deleteUser() throws Exception {
        try {
            user = this.userService.getTUser(id);
            user.setIsdeleted("Y");
            this.userService.updateTUser(user);

            return "success";
        } catch (Exception e) {
            return "deleteError";
        }
    }

    /**
     * **************************************************
     * get & set methods
     * ************************************************
     */

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @JSON(serialize = false)
    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    @JSON(serialize = false)
    public List<TUser> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<TUser> listUsers) {
        this.listUsers = listUsers;
    }

    @JSON(serialize = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @JSON(serialize = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JSON(serialize = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JSON(serialize = false)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @JSON(serialize = false)
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @JSON(serialize = false)
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @JSON(serialize = false)
    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @JSON(serialize = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserService getUserService() {
        return userService;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Boolean getStatu() {
        return statu;
    }

    public void setStatu(Boolean statu) {
        this.statu = statu;
    }

}
