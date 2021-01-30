import request from '@/utils/request'

        
// 分页查询列表
export function listPageArea(query) {
  return request({
    url: '/admin/area/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询列表
export function listArea(query) {
  return request({
    url: '/admin/area/findList',
    method: 'post',
    params: query
  })
}

// 查询详细
export function getArea(baCode) {
  return request({
    url: '/admin/area/' + baCode,
    method: 'get'
  })
}

// 新增
export function addArea(data) {
  return request({
    url: '/admin/area',
    method: 'post',
    data: data
  })
}

// 修改
export function updateArea(data) {
  return request({
    url: '/admin/area',
    method: 'put',
    data: data
  })
}

// 删除
export function delArea(baCode) {
  return request({
    url: '/admin/area/' + baCode,
    method: 'delete'
  })
}


// 状态启用/停用
export function changeStatus(data) {
  return request({
    url: '/admin/area/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出
export function exportArea(query) {
  return request({
    url: '/admin/area/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}