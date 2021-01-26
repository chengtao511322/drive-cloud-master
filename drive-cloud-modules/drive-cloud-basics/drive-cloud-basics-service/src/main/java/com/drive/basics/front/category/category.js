import request from '@/utils/request'


// 分页查询产品分类表列表
export function listPageCategory(query) {
  return request({
    url: '/basics/category/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询产品分类表列表
export function listCategory(query) {
  return request({
    url: '/basics/category/findList',
    method: 'post',
    params: query
  })
}

// 查询产品分类表详细
export function getCategory(id) {
  return request({
    url: '/basics/category/' + id,
    method: 'get'
  })
}

// 新增产品分类表
export function addCategory(data) {
  return request({
    url: '/basics/category',
    method: 'post',
    data: data
  })
}

// 修改产品分类表
export function updateCategory(data) {
  return request({
    url: '/basics/category',
    method: 'put',
    data: data
  })
}

// 删除产品分类表
export function delCategory(id) {
  return request({
    url: '/basics/category/' + id,
    method: 'delete'
  })
}


// 状态启用/停用产品分类表
export function changeStatus(data) {
  return request({
    url: '/basics/category/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出产品分类表
export function exportCategory(query) {
  return request({
    url: '/basics/category/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}