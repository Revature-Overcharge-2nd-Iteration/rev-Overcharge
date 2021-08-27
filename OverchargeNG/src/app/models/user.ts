import { ObjectivesComponent } from "../components/objectives/objectives.component";
import { Objective } from "./objective";

export class User {
    id: number;
    username: string;
    password: string;
    points: number;
    last_login_on: number;
    objectives: Objective[];
   
  
   
    constructor(id: number,username: string, password: string,  points: number, last_login_on: number){
        this.id = id || -1;
        this.username = username;
        this.password  = password;
        this.points = points || 0;
        this.last_login_on = last_login_on || 0;
       
    }
}
