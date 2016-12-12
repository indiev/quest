import User from './User'
let loginUserInstance = null;

class LoginUser extends User {
 	constructor(...args) {
 		super(...args);
 		this.accessTime = new Data();
 	}
 	login() {
 		if(!loginUserInstance) {
 			loginUserInstance = this;
 			this.loginTime = new Data();
 		}
 	}
 	logout() {
 		if(loginUserInstance) {
 			loginUserInstance = null;
 			this.logoutTime = new Date();
 		}
 	}
 	isLogin() {
 		if(loginUserInstance) {
 			return true;
 		}
 		return false;
 	}
 }
 
 export default LoginUser;