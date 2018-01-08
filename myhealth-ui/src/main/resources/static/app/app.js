'use strict';
var myHealthApp = angular.module('myHealthApp', [
    'ui.bootstrap',
    'ui.router',
    'ngResource',
    'ngCookies',
    'pascalprecht.translate',
    'ngSanitize',
    'ngMessages',
    'angularUtils.directives.dirPagination',
    'ngAnimate',
    'ngFileUpload',
    'angular-jwt',
    'angular-storage'
]).config(function ($httpProvider, jwtInterceptorProvider) {
    jwtInterceptorProvider.tokenGetter = function (store) {
        return store.get('jwt');
    };
    $httpProvider.interceptors.push('jwtInterceptor');
    $httpProvider.interceptors.push(apiInterceptor);
}).config(function (jwtOptionsProvider) {
    jwtOptionsProvider.config({
        whiteListedDomains: [API_URL, 'localhost']
    });
});



// api url prefix
var API_URL = 'http://localhost:8081';

function apiInterceptor($q) {
    return {
        request: function (config) {
            var url = config.url;

            // ignore template requests
            if (url.substr(url.length - 5) == '.html') {
                return config || $q.when(config);
            }

            config.url = API_URL + config.url;

            return config || $q.when(config);
        }
    }
}

myHealthApp.config(function ($urlRouterProvider, $httpProvider) {
    $httpProvider.defaults.headers['X-Frame-Options'] = 'DENY';
    $urlRouterProvider.otherwise('/my/home');
});

myHealthApp.config(function ($stateProvider) {
    $stateProvider.state('external', {
        url: '/',
        abstract: true,
        controller: 'DashboardController',
        templateUrl: "app/content/dashboard/dashboard.html",
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'app/content/login/login.html',
        controller: 'LoginController',
        data: {
            requireLogin: false
        }
    });

    $stateProvider.state('registration', {
        url: '/register',
        templateUrl: 'app/content/registration/registration.html',
        controller: 'RegistrationController',
        data: {
            requireLogin: false
        }
    });

    $stateProvider.state('forgot-password', {
        url: '/forgot-password',
        templateUrl: 'app/content/restore-password/forgot-password.html',
        controller: 'ForgotPasswordController',
        data: {
            requireLogin: false
        }
    });

    $stateProvider.state('reset-password', {
        url: '/reset-password/:hash',
        templateUrl: 'app/content/restore-password/reset-password.html',
        controller: 'ResetPasswordController',
        data: {
            requireLogin: false
        }
    });

    $stateProvider.state('activation', {
        url: '/activation/:hash',
        templateUrl: 'app/content/activation/activation.html',
        controller: 'ActivationController',
        data: {
            requireLogin: false
        }
    });

    $stateProvider.state('protected.home', {
        url: '/home',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/welcome.html"
            }
        }
    });

    $stateProvider.state('protected.profile', {
        url: '/profile',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/profile/profile.html",
                controller: "ProfileController"
            }
        }
    });

    $stateProvider.state('protected', {
        url: '/my',
        templateUrl: "app/content/dashboard/dashboard.html",
        controller: 'DashboardController',
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.measurements', {
        url: '/measurements',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/measurements/measurements.html",
                controller: 'MeasurementsController'
            }
        },
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.health-statement', {
        url: '/health-statement',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/health-statement.html"
            }
        },
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.self-health-check', {
        url: '/self-health-check',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/self-health-check.html"
            }
        },
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.medical-profile', {
        url: '/medical-profile',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/medical-profile.html"
            }
        },
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.live-chart', {
        url: '/live-chart',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/live-chart.html"
            }
        },
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.notes', {
        url: '/notes',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/notes/notes.html",
                controller: "NoteController"
            }
        },
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.ihealth-import', {
        url: '/ihealth-import',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/ihealth/ihealth-import.html",
                controller: "IHealthImportController"
            }
        },
        data: {
            requireLogin: true
        }
    });

    $stateProvider.state('protected.ihealth-handle-redirect', {
        url: '/ihealth/redirect_for_code',
        views: {
            'content': {
                templateUrl: "app/mainpage/center/content/ihealth/ihealth-import.html",
                controller: "IHealthImportController"
            }
        },
        data: {
            redirect: true
        }
    });
});

myHealthApp.run(function ($rootScope, $http, $state, store) {
    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams, options) {
        //var currentUser = AuthService.getCurrentUser();
        var token = store.get('jwt');
        if (toState.data.requireLogin && (!token)) {
            event.preventDefault();

            $state.go('login', {}, {
                reload: true
            });
        }
    });
});

myHealthApp.filter('jsDate', function () {
    return function (x) {
        return new Date(x);
    }
});