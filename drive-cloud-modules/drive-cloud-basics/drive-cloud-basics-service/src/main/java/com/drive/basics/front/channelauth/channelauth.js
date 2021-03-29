import request from '@/utils/request'

              
// 分页查询菜单 按钮 用户拥有权限管理列表
export function listPageChannelAuth(query) {
  return request({
    url: '/basics/channelAuth/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询菜单 按钮 用户拥有权限管理列表
export function listChannelAuth(query) {
  return request({
    url: '/basics/channelAuth/findList',
    method: 'get',
    params: query
  })
}

// 查询菜单 按钮 用户拥有权限管理详细
export function getChannelAuth(channelId) {
  return request({
    url: '/basics/channelAuth/' + channelId,
    method: 'get'
  })
}

// 新增菜单 按钮 用户拥有权限管理
export function addChannelAuth(data) {
  return request({
    url: '/basics/channelAuth',
    method: 'post',
    data: data
  })
}

// 修改菜单 按钮 用户拥有权限管理
export function updateChannelAuth(data) {
  return request({
    url: '/basics/channelAuth',
    method: 'put',
    data: data
  })
}

// 删除菜单 按钮 用户拥有权限管理
export function delChannelAuth(channelId) {
  return request({
    url: '/basics/channelAuth/' + channelId,
    method: 'delete'
  })
}


// 状态启用/停用菜单 按钮 用户拥有权限管理
export function changeStatus(data) {
  return request({
    url: '/basics/channelAuth/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出菜单 按钮 用户拥有权限管理
export function exportChannelAuth(query) {
  return request({
    url: '/basics/channelAuth/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}