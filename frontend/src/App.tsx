import { PrimeReactProvider } from 'primereact/api';
import Router from './routes';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ToastContextProvider } from './context/toast-context';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
    },
    mutations: {
      retry: 1,
    }
  }
})

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <PrimeReactProvider>
        <ToastContextProvider>
          <Router />
        </ToastContextProvider>
      </PrimeReactProvider>
    </QueryClientProvider>
  );
}

export default App;
