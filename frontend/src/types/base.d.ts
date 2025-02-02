export interface Result {
    success: boolean;
    message: string;
    errors: Map<string, string>;
}