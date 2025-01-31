import { useMutation, useQuery } from "@tanstack/react-query";
import { LoginRequest } from "../types/login";
import Service from "../service";
import { RegisterRequest } from "../types/register";
import { UrlCreateRequest } from "../types/url";

export const useLoginMutation = () =>
    useMutation({
        mutationFn: (request: LoginRequest) => Service.login(request),
    })

export const useRegisterMutation = () =>
    useMutation({
        mutationFn: (request: RegisterRequest) => Service.register(request),
    })

export const useTokenCheckQuery = () =>
    useQuery({
        queryKey: ['token-check'],
        queryFn: () => Service.tokenCheck(),
    })

export const useCreateShortUrlMutation = () =>
    useMutation({
        mutationFn: (request: UrlCreateRequest) => Service.createShortUrl(request),
    })