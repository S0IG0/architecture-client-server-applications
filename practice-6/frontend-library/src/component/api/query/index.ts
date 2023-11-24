import {gql} from "@apollo/client";


export const FindLibraries = gql(
    `query FindLibraries {
    findLibraries {
        id
        name
        address
    }
}`)