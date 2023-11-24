import {ReactNode} from "react";
import {HomePage} from "@page/HomePage.tsx";
import {LibrariesPage} from "@page/LibrariesPage.tsx";

export interface Page {
    name: string
    path: string
    component: ReactNode
}

export enum NamePages {
    HOME,
    ABOUT,
}

export const routes: { [key in NamePages]: Page } = {
    [NamePages.HOME]: {
        name: "Home",
        path: "/",
        component: <HomePage/>
    } as Page,
    [NamePages.ABOUT]: {
        name: "Libraries",
        path: "/libraries",
        component: <LibrariesPage/>
    } as Page,
}

