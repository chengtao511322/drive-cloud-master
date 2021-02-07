import request from '@/utils/request'

                              
// 分页查询平台应用版本表列表
export function listPageAppVersion(query) {
  return request({
    url: '/admin/appVersion/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询平台应用版本表列表
export function listAppVersion(query) {
  return request({
    url: '/admin/appVersion/findList',
    method: 'post',
    params: query
  })
}

// 查询平台应用版本表详细
export function getAppVersion(id) {
  return request({
    url: '/admin/appVersion/' + id,
    method: 'get'
  })
}

// 新增平台应用版本表
export function addAppVersion(data) {
  return request({
    url: '/admin/appVersion',
    method: 'post',
    data: data
  })
}

// 修改平台应用版本表
export function updateAppVersion(data) {
  return request({
    url: '/admin/appVersion',
    method: 'put',
    data: data
  })
}

// 删除平台应用版本表
export function delAppVersion(id) {
  return request({
    url: '/admin/appVersion/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台应用版本表
export function changeStatus(data) {
  return request({
    url: '/admin/appVersion/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台应用版本表
export function exportAppVersion(query) {
  return request({
    url: '/admin/appVersion/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}