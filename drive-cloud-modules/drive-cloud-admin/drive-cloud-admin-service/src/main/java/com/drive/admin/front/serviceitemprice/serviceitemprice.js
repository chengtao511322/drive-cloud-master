import request from '@/utils/request'


// 分页查询服务项目价格表列表
export function listPageServiceItemPrice(query) {
  return request({
    url: '/admin/serviceItemPrice/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询服务项目价格表列表
export function listServiceItemPrice(query) {
  return request({
    url: '/admin/serviceItemPrice/findList',
    method: 'post',
    params: query
  })
}

// 查询服务项目价格表详细
export function getServiceItemPrice(id) {
  return request({
    url: '/admin/serviceItemPrice/' + id,
    method: 'get'
  })
}

// 新增服务项目价格表
export function addServiceItemPrice(data) {
  return request({
    url: '/admin/serviceItemPrice',
    method: 'post',
    data: data
  })
}

// 修改服务项目价格表
export function updateServiceItemPrice(data) {
  return request({
    url: '/admin/serviceItemPrice',
    method: 'put',
    data: data
  })
}

// 删除服务项目价格表
export function delServiceItemPrice(id) {
  return request({
    url: '/admin/serviceItemPrice/' + id,
    method: 'delete'
  })
}


// 状态启用/停用服务项目价格表
export function changeStatus(data) {
  return request({
    url: '/admin/serviceItemPrice/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出服务项目价格表
export function exportServiceItemPrice(query) {
  return request({
    url: '/admin/serviceItemPrice/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}