import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../_services/auth.service";

@Component({
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
    login: string;
    password: string;
    email: string;
    submitDisabled: boolean;

    constructor(private authService: AuthService) {
        this.submitDisabled = false;
    }

    ngOnInit() {
    }

    register() {
        this.submitDisabled = true;
        this.authService.register(this.login, this.email, this.password).subscribe(data => {
            alert("Registration link was sent to you email.");
            console.log(data);
            this.submitDisabled = false;
        }, error => {
            alert("Oo, error");
            console.log(error);
            this.submitDisabled = false;
        })
        // this.loading = true;
        // this.authenticationService.login(this.model.username, this.model.password)
        //     .subscribe(
        //         data => {
        //             this.router.navigate([this.returnUrl]);
        //         },
        //         error => {
        //             this.alertService.error(error);
        //             this.loading = false;
        //         });
    }

}
