import {createContext, useContext, useEffect, useState} from "react";
import axios from "axios";

const AuthContext = createContext()

export function AuthContextProvider({children}){
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user"))) 

    function signUp(data) {
        return axios.post(`${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/users`, data).then(({data: currentUser}) => {
            localStorage.setItem("user", JSON.stringify(currentUser))
            setUser(currentUser)
        })
    }

    function logIn(email, password) {
        return axios.post(`${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/users/login`, {
            email, 
            password
        }).then(({data: currentUser}) => {
            localStorage.setItem("user", JSON.stringify(currentUser))
            setUser(currentUser)
        })
    }


    function logOut(){
        localStorage.removeItem("user")
        setUser(null)
    }

    return ( 
        <AuthContext.Provider value = {{signUp, logIn, logOut, user}}>
            {children}
        </AuthContext.Provider>
    );
}

export function UserAuth(){
    return useContext(AuthContext)
}