import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QBSettings } from '../_properties/properties';
import {Observable} from "rxjs/Observable";

interface LoginResponse {
    sessionId: string;
}

@Injectable()
export class AuthService {
    constructor(private http: HttpClient) { }

    login(login: string, password: string) {
        const loginRequest = {
            email: login,
            password: password
        };
        console.log("Login into queuebrain: " + loginRequest);

        return this.http.post<LoginResponse>(QBSettings.serverUrl + "/login?type=loginpass", loginRequest, {withCredentials: true});
    }

    register(login: string, email: string, password: string) {
        const registrationRequest = {
            name: login,
            email: email,
            password: password
        };
        console.log("Registering in queuebrain: " + registrationRequest);

        return this.http.post(QBSettings.serverUrl + "/registration?type=loginpass", registrationRequest, {withCredentials: true});
    }

    completeRegistration(reglink: string) {
        const reglinkRequest = {
            registrationLink: reglink
        };
        console.log("Completing registering in queuebrain: " + reglinkRequest);

        return this.http.post<LoginResponse>(QBSettings.serverUrl + "/login?type=first", reglinkRequest, {withCredentials: true});

    }
}