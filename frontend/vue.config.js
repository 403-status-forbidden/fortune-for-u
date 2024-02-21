const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,

  // npm run build 타겟 디렉토리 (백엔드)
  outputDir: "../backend/src/main/resources/static",

  devServer: {
    proxy: {
      '/': {
        target: process.env.VUE_APP_HOST_NAME,
        changeOrigin : true,
      } 
    }
  }
});

//vue.config.js
module.exports = {
  css: {
    loaderOptions: {
      sass: {
        additionalData: `
                @import "@/assets/scss/reset.scss";
              `,
      },
    },
  },
};
