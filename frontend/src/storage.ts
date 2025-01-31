class LocalStorage {
    public static saveToLocalStorage(key: string, value: any) : void {
        localStorage.setItem(key, value);
    }

    public static saveObjectToLocalStorage(key: string, value: any) : void {
        localStorage.setItem(key, JSON.stringify(value));
    }

    public static getFromLocalStorage(key: string) : string | null {
        return localStorage.getItem(key);
    }

    public static getObjectFromLocalStorage(key: string) : any {
        return JSON.parse(LocalStorage.getFromLocalStorage(key) || '{}');
    }
}

export default LocalStorage;