import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig({
  // https config
  // server:{
  //   https:{
  //     cert:'./certificado.pem',
  //     key:'./clave_privada.pem'
  //   }
  // },
  plugins: [
    react(),
  ],
})
