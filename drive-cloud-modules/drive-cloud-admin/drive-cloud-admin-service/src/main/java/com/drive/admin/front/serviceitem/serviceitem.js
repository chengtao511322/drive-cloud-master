import request from '@/utils/request'


// 分页查询服务项目表列表
export function listPageServiceItem(query) {
  return request({
    url: '/admin/serviceItem/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询服务项目表列表
export function listServiceItem(query) {
  return request({
    url: '/admin/serviceItem/findList',
    method: 'post',
    params: query
  })
}

// 查询服务项目表详细
export function getServiceItem(id) {
  return request({
    url: '/admin/serviceItem/' + id,
    method: 'get'
  })
}

// 新增服务项目表
export function addServiceItem(data) {
  return request({
    url: '/admin/serviceItem',
    method: 'post',
    data: data
  })
}

// 修改服务项目表
export function updateServiceItem(data) {
  return request({
    url: '/admin/serviceItem',
    method: 'put',
    data: data
  })
}

// 删除服务项目表
export function delServiceItem(id) {
  return request({
    url: '/admin/serviceItem/' + id,
    method: 'delete'
  })
}


// 状态启用/停用服务项目表
export function changeStatus(data) {
  return request({
    url: '/admin/serviceItem/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出服务项目表
export function exportServiceItem(query) {
  return request({
    url: '/admin/serviceItem/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}