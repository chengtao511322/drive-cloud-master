import request from '@/utils/request'


// 分页查询服务项目打包明细表列表
export function listPageServicePackageDetail(query) {
  return request({
    url: '/admin/servicePackageDetail/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询服务项目打包明细表列表
export function listServicePackageDetail(query) {
  return request({
    url: '/admin/servicePackageDetail/findList',
    method: 'post',
    params: query
  })
}

// 查询服务项目打包明细表详细
export function getServicePackageDetail(id) {
  return request({
    url: '/admin/servicePackageDetail/' + id,
    method: 'get'
  })
}

// 新增服务项目打包明细表
export function addServicePackageDetail(data) {
  return request({
    url: '/admin/servicePackageDetail',
    method: 'post',
    data: data
  })
}

// 修改服务项目打包明细表
export function updateServicePackageDetail(data) {
  return request({
    url: '/admin/servicePackageDetail',
    method: 'put',
    data: data
  })
}

// 删除服务项目打包明细表
export function delServicePackageDetail(id) {
  return request({
    url: '/admin/servicePackageDetail/' + id,
    method: 'delete'
  })
}


// 状态启用/停用服务项目打包明细表
export function changeStatus(data) {
  return request({
    url: '/admin/servicePackageDetail/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出服务项目打包明细表
export function exportServicePackageDetail(query) {
  return request({
    url: '/admin/servicePackageDetail/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}