<template>
    <div>
        <el-container>
            <el-header style="background-color: #687179">
                <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px;">
                <span style="font-size: 30px; margin-left: 30px; color: white">漏洞管理平台</span>

                <el-dropdown style="float: right; height: 60px; line-height: 60px">
                    <span class="el-dropdown-link" style="color: white; font-size: 16px">{{ user.name }}<i class="el-icon-arrow-down el-icon--right"></i></span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                            <div @click="logout">退出登录</div>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
        </el-container>

        <el-container>
            <el-aside style="overflow: hidden; min-height: 100vh; background-color: #545c64; width: 250px">

                <el-menu :default-active="$route.path" router background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">

                    <el-menu-item index="/">
                        <i class="el-icon-s-home"></i>
                        <span slot="title">系统首页</span>
                    </el-menu-item>



                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-location"></i><span>漏洞管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="3-1">xxx信息</el-menu-item>
                            <el-menu-item index="3-2">yyy信息</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>



                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-location"></i><span>资产管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/srclist">SRC列表</el-menu-item>
                            <el-menu-item index="/bugBounty">众测平台</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-location"></i><span>SCA</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="4-1">创建扫描</el-menu-item>
                            <el-menu-item index="4-2">组件配置</el-menu-item>
                            <el-menu-item index="4-2">扫描任务</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-location"></i><span>CodeQL</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="5-1">CodeQL基础</el-menu-item>
                            <el-menu-item index="5-2">代码审计基础</el-menu-item>
                            <el-menu-item index="/gitcode">代码管理</el-menu-item>
                            <el-menu-item index="5-3">知识库</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="6">
                        <template slot="title">
                            <i class="el-icon-location"></i><span>Tscan</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="6-1">配置管理</el-menu-item>
                            <el-menu-item index="6-2">创建扫描</el-menu-item>
                            <el-menu-item index="6-3">任务列表</el-menu-item>
                            <el-menu-item index="6-4">漏洞管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="7">
                        <template slot="title">
                            <i class="el-icon-location"></i><span>测试目录</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/books">图书管理</el-menu-item>
                            <el-menu-item index="/booktype">图书分类</el-menu-item>

                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="8">
                        <template slot="title">
                            <i class="el-icon-location"></i><span>用户管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/adminuser">管理员信息</el-menu-item>
                            <el-menu-item index="/user">用户管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>



                </el-menu>

            </el-aside>
            <el-main>
                <router-view/>
            </el-main>
        </el-container>

    </div>
</template>

<script>
    export default {
        name: "layout.vue",
        data(){
            return{
                user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}
            }
        },
        methods:{
            logout(){
                localStorage.removeItem("user");
                this.$router.push("/login")
            }
        }
    }
</script>

<style>
    .el-menu{
        border-right: none !important;
    }
</style>