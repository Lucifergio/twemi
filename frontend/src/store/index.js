import { createStore } from 'vuex'

export default createStore({
    state: {
        token: localStorage.getItem('user-token') || '',
        role: localStorage.getItem('user-role') || '',
        username: localStorage.getItem('user-name') || '',
        authorities: localStorage.getItem('authorities') || '',
    },

    mutations: {
        auth_login(state, user) {
            localStorage.setItem('user-token', user.token);
            localStorage.setItem('user-name', user.name);
            // localStorage.setItem('user-authorities', user.roles);
            state.token = user.token;
            state.username = user.username;
            state.authorities = user.roles;
            // let isUser = false;
            // let isAdmin = false;
            // console.log(user.)
            // for (let i = 0; i < user.roles.length; i++) {
            //     if (user.roles[i].authority === 'USER') {
            //         isUser = true;
            //     } else if (user.roles[i].authority === 'ADMIN') {
            //         isAdmin = true;
            //     }
            // }
            // if (isUser) {
                localStorage.setItem('user-role', 'user');
                state.role = 'user';
            // }
            // if (isAdmin) {
                localStorage.setItem('user-role', 'admin');
                state.role = 'admin';
            // }
        },
        auth_logout() {
         /*   // eslint-disable-next-line no-undef
            state.token = '';
            // eslint-disable-next-line no-undef
            state.role = '';
            // eslint-disable-next-line no-undef
            state.username = '';
            // eslint-disable-next-line no-undef
            state.authorities = [];*/
            localStorage.removeItem('user-token');
            localStorage.removeItem('user-role');
            localStorage.removeItem('user-name');
            localStorage.removeItem('user-authorities');
        }
    },
    actions: {
        login(context, user)  {
            context.commit('auth_login', user)
        },
        logout: (context) => {
            context.commit('auth_logout');
        },
    },
    getters: {
        isAuthenticated: state => {
            return state.token != null && state.token !== '';
        },
        isAdmin: state => {
            return state.role === 'admin';
        },
        getUsername: state => {
            return state.username;
        },
        getAuthorities: state => {
            return state.authorities;
        },
        getToken: state => {
            return state.token;
        }
    }
});