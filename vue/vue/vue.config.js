const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})


module.exports = {
  devServer: {

    port: 9002 // 此处修改你想要的端口号

  }

}
