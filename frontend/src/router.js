import {createRouter, createWebHistory} from "vue-router";

const HomePage = () => import('./components/HomePage.vue')
const SignIn = () => import('./components/SignIn.vue')
const SignUp = () => import('./components/SignUp.vue')
const UserPage = () => import('./components/AdminPage.vue')
const AdminPage = () => import('./components/UserPage.vue')
const ProjectPage = () => import('./components/ProjectPage.vue')
const CreateProjectPage = () => import('./components/CreateProjectPage.vue')

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePage
    },
    // {
    //     path: '/home',
    //     name: 'HomePage',
    //     component: HomePage
    // },
    {
        path: '/login',
        name: 'SignIn',
        component: SignIn
    },
    {
        path: '/register',
        name: 'SignUp',
        component: SignUp
    },
    {
        path: '/user',
        name: 'UserPage',
        component: UserPage
    },

    {
        path: '/admin',
        name: 'AdminPage',
        component: AdminPage
    },

    {
        path: '/project',
        name: 'ProjectPage',
        component: ProjectPage
    },

    {
        path: '/create-project',
        name: 'CreateProjectPage',
        component: CreateProjectPage
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router