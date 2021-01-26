import request from '@/utils/request'


// 分页查询栏目列表
export function listPageChannel(query) {
  return request({
    url: '/basics/channel/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询栏目列表
export function listChannel(query) {
  return request({
    url: '/basics/channel/findList',
    method: 'post',
    params: query
  })
}

// 查询栏目详细
export function getChannel(${pkColumn.propertyName}) {
  return request({
    url: '/basics/channel/' + ${pkColumn.propertyName},
    method: 'get'
  })
}

// 新增栏目
export function addChannel(data) {
  return request({
    url: '/basics/channel',
    method: 'post',
    data: data
  })
}

// 修改栏目
export function updateChannel(data) {
  return request({
    url: '/basics/channel',
    method: 'put',
    data: data
  })
}

// 删除栏目
export function delChannel(${pkColumn.propertyName}) {
  return request({
    url: '/basics/channel/' + ${pkColumn.propertyName},
    method: 'delete'
  })
}


// 状态启用/停用栏目
export function changeStatus(data) {
  return request({
    url: '/basics/channel/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出栏目
export function exportChannel(query) {
  return request({
    url: '/basics/channel/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}