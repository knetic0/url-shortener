import { Result } from "./base";

export interface Url {
    shortUrl: string;
    originalUrl: string;
}

export interface UrlCreateRequest {
    originalUrl: string;
}

export interface UrlCreateResponse extends Result {
    data: Url;
}

export interface UrlComponentProps {
    urls: Url[];
}

export interface UrlsDataTableFields {
    field: string;
    header: string;
}