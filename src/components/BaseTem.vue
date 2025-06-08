<template>
  <div id="base-tem">
    <!-- 顶部header -->
    <header class="header">
        <h1 class="title">线上考试平台</h1>
    </header>

    <!-- 左侧nav -->
    <div class="main-container">
        <nav class="nav-container">
            <router-link 
            v-for="item in navItems" 
            :key="item.path" 
            :to="item.path" 
            class="nav-item" 
            active-class="active"
            >
            {{ item.title }}
            </router-link>
        </nav>
    </div>

    右侧内容区
    <div class="content">
        <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
    name:'BaseTem',
    props:{
        navItems:{
            type:Array,
            required:true
        }
    }
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Microsoft YaHei', sans-serif;
}

body {
    background-color: #f0f4f8;
    min-height: 100vh;
}

#base-tem {
    display: flex;
    flex-direction: column;
    height: 100vh;
}

.header {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    height: 60px;
    background: #4a7ac7;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
    z-index: 100;
    display: flex;
    align-items: center;
    padding: 0 20px;
}

.title {
    color: #e2e8f0;
    font-size: 1.5rem;
    font-weight: 600;
    letter-spacing: 1px;
}

.main-container {
    display: flex;
    margin-top: 60px;
    height: calc(100vh - 60px);
    position: relative;
}

.nav-container {
    width: 220px;
    background-color: #4a7ac7; 
    height: 100%;
    position: fixed;
    left: 0;
    top: 60px;
    bottom: 0;
    overflow-y: auto;
    transition: all 0.3s ease;
    box-shadow: 2px 0 15px rgba(0, 0, 0, 0.18);
}

.nav-item {
    display: block;
    padding: 16px 20px;
    color: #e2e8f0; 
    text-decoration: none;
    font-size: 0.95rem;
    transition: all 0.2s ease;
}

.nav-item:hover,
.nav-item.active {
    background-color: #3e67b5;
    color: white;
}

.nav-container::after {
    content: "";
    position: absolute;
    right: 0;
    top: 0;
    bottom: 0;
    width: 15px;
    background: linear-gradient(to right, #4a7ac7, transparent);
    pointer-events: none;
}

/* 右侧内容区样式 */
.content {
    flex: 1;
    margin-left: 220px;
    padding: 30px;
    background-color: #f8fafc;
    min-height: 100%;
    position: relative;
}

.content::before {
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    width: 20px;
    background: linear-gradient(to right, rgba(0, 0, 0, 0.05), transparent);
}
</style>