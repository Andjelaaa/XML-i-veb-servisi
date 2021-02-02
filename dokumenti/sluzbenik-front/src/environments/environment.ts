// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,
      baseUrl: 'http://localhost:8081',
      login: 'api/user/login',
      signOut:  'api/user/sign-out',
      register:  'api/user/register',
      newRequest:  'api/zahtev/create',
    newInformation: 'api/obavestenje/create',
    allInformations: 'api/obavestenje/allInformations',
    userInformations: 'api/obavestenje/userInformations',
		obavestenje: 'api/obavestenje',
      	zahtev: 'api/zahtev'  };

