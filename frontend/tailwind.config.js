/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        
        white: "#FFFFFF",
        light_gray_1: "#EFEFEF",
        light_gray_2: "#BEBEBE",
        light_gray_3: "#5A5A5A",
        dark_gray_1: "#292929",
        dark_gray_2: "#1F1F1F",
        black: "#0A0A0A",
        warning_red: "#D90824",
        brand_orange: "#FF6700",
        honolulu_blue: "#167DB7",
        valid_green: "#1F883D",
        youtube_red: "#FF0000",
        linkedin_blue: "#0270AD",
        github_black: "#1F2328",
        portfolio: "#954C1B"
      },
      fontFamily: {
        roboto: ["Roboto", "sans-serif"]
      },
    },
  },
  plugins: [],
}

