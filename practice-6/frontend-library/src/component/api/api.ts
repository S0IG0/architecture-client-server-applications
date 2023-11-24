import {ApolloClient, createHttpLink, InMemoryCache} from "@apollo/client";

const API_HOST = "localhost"
const API_PROTOCOL = "http"
const API_PORT = 80
const API_ENDPOINT = "graphql"


export const API_URL = `${API_PROTOCOL}://${API_HOST}:${API_PORT}/${API_ENDPOINT}`

export const link = createHttpLink({
    uri: API_URL
});
export const client = new ApolloClient({
    cache: new InMemoryCache(),
    link
});