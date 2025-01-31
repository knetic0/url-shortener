import { DataTable } from "primereact/datatable";
import { Url, UrlComponentProps, UrlsDataTableFields } from "../types/url";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { ToastContextProps } from "../types/toast-context";
import { useToastContext } from "../hooks/use-toast-context";
import { InputText } from 'primereact/inputtext';
import { IconField } from 'primereact/iconfield';
import { InputIcon } from 'primereact/inputicon';
import { useState } from "react";
import { FilterMatchMode } from "primereact/api";

const columns : UrlsDataTableFields[]  = [
    { field: 'shortUrl', header: 'Short URL' },
    { field: 'originalUrl', header: 'Original URL' },
    { field: 'action', header: 'Action' }
]

const baseURL : string | undefined = process.env.REACT_APP_API_BASE_URL;

const Urls = ({ urls }: UrlComponentProps) => {

    const { showToast } : ToastContextProps = useToastContext();

    const [globalFilterValue, setGlobalFilterValue] = useState<string>('');
    const [filters, setFilters] = useState({
        global: { value: '', matchMode: FilterMatchMode.CONTAINS }
    });

    const getFullUrl = (shortUrl: string) => {
        return `${baseURL}/${shortUrl}`;
    }

    const onGlobalFilterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        let _filters = { ...filters };
        _filters['global'].value = value;
        setFilters(_filters);
        setGlobalFilterValue(value);
    };

    const copyToClipboard = (shortUrl: string) => {
        const url : string = getFullUrl(shortUrl);
        navigator.clipboard.writeText(url)
            .then(() => showToast({ severity: 'success', summary: 'Success', detail: 'Copied to clipboard' }))
            .catch(() => showToast({ severity: 'error', summary: 'Error', detail: 'An error occurred while copying to clipboard' }));
    }

    const redirectToUrl = (shortUrl: string) => {
        const url : string = getFullUrl(shortUrl);
        window.open(url, "_blank");
    }

    const actionBody = (rowData: Url) => {
        return (
            <div className="flex gap-3">
                <Button onClick={() => redirectToUrl(rowData.shortUrl)} icon="pi pi-send" severity="info" />
                <Button icon="pi pi-copy" severity="contrast" onClick={() => copyToClipboard(rowData.shortUrl)} />
            </div>
        )
    }

    const renderColumns: (JSX.Element | undefined)[] = columns.map((col: UrlsDataTableFields) => {
        if (col.field === 'shortUrl') {
            return <Column key={col.field} field={col.field} header={col.header} />;
        } else if (col.field === 'originalUrl') {
            return <Column key={col.field} field={col.field} header={col.header} />;
        } else if (col.field === 'action') {
            return <Column key={col.field} field={col.field} header={col.header} body={actionBody} />;
        }
    })

    const renderHeader = () => {
        return (
            <div className="flex justify-content-between">
                <IconField iconPosition="left">
                    <InputIcon className="pi pi-search" />
                    <InputText value={globalFilterValue} onChange={onGlobalFilterChange} placeholder="Keyword Search" />
                </IconField>
            </div>
        );
    };

    return (
        <DataTable header={renderHeader()} filters={filters} globalFilterFields={['shortUrl', 'originalUrl']} paginator stripedRows rows={5} value={urls} tableStyle={{ minWidth: '50rem', width: '60rem' }}>
            {renderColumns}
        </DataTable>
    )
}

export default Urls;